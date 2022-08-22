package ru.makcnm4ik.vk_news.util;

import ru.makcnm4ik.vk_news.config.ConfigYML;
import ru.makcnm4ik.vk_news.db.HibernateDB;
import ru.makcnm4ik.vk_news.db.YMLDB;

public class DataUtil {
    public static void updateData() {
        if (ConfigYML.getInstance().isUseSQL()) new HibernateDB(HibernateUtil.getSessionFactory()).updateData();
        else new YMLDB().updateData();
    }

    public static void saveData() {
        if (ConfigYML.getInstance().isUseSQL()) new HibernateDB(HibernateUtil.getSessionFactory()).saveData();
        else new YMLDB().saveData();
    }
}
