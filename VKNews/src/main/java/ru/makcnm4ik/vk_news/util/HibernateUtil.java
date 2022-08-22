package ru.makcnm4ik.vk_news.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.makcnm4ik.vk_news.VKNews;
import ru.makcnm4ik.vk_news.config.ConfigYML;
import ru.makcnm4ik.vk_news.db.entity.LastPostId;
import ru.makcnm4ik.vk_news.db.entity.SeenPlayer;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            String ip = ConfigYML.getInstance().getIp();
            int port = ConfigYML.getInstance().getPort();
            String username = ConfigYML.getInstance().getUsername();
            String password = ConfigYML.getInstance().getPassword();

            try {
                Configuration configuration = new Configuration().configure();

                configuration.setProperty(
                        "hibernate.connection.url",
                        String.format("jdbc:mysql://%s:%d/vk_news_db?createDatabaseIfNotExist=true", ip, port)
                );
                configuration.setProperty("default_schema", "vk_news_db");
                configuration.setProperty("hibernate.connection.username", username);
                configuration.setProperty("hibernate.connection.password", password);

                configuration.addAnnotatedClass(SeenPlayer.class);
                configuration.addAnnotatedClass(LastPostId.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                VKNews.getInstance().disablePlugin(e, "Can not get SessionFactory. Maybe wrong settings of HibernateDB");
            }
        }

        return sessionFactory;
    }
}
