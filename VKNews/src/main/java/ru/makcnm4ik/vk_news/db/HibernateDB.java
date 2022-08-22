package ru.makcnm4ik.vk_news.db;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.makcnm4ik.vk_news.VKNews;
import ru.makcnm4ik.vk_news.data.NewsData;
import ru.makcnm4ik.vk_news.db.entity.LastPostId;
import ru.makcnm4ik.vk_news.db.entity.SeenPlayer;
import ru.makcnm4ik.vk_news.vk.VKResponseResult;

import java.util.ArrayList;
import java.util.List;

public class HibernateDB {
    private final SessionFactory sessionFactory;

    public HibernateDB(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void updateData() {
        NewsData.seenPlayers = getSeenPlayerUuidList();
        NewsData.lastResponseResult = new VKResponseResult(
                getLastPostId(),
                "Automatically created",
                "Automatically created"
        );
    }

    public void saveData() {
        clearTable("LastPostId");
        clearTable("SeenPlayer");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(new LastPostId(NewsData.lastResponseResult.getId()));

            System.out.println(NewsData.lastResponseResult.getId());

            for (String uuid : NewsData.seenPlayers) session.save(new SeenPlayer(uuid));

            NewsData.seenPlayers.forEach(g -> System.out.println(g));

            transaction.commit();
        } catch (Exception e) {
            VKNews.getInstance().printWarn(e, "clearTable error");
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    private void clearTable(String tableName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.createQuery(String.format("DELETE FROM %s", tableName)).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            VKNews.getInstance().printWarn(e, "clearTable error");
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    private List<String> getSeenPlayerUuidList() {
        List<String> resultList = new ArrayList<>();
        Session session = sessionFactory.openSession();

        try {
            Query query = session.createQuery("FROM SeenPlayer");
            List<SeenPlayer> queryResult = (List<SeenPlayer>) query.getResultList();
            queryResult.forEach(seenPlayer -> resultList.add(seenPlayer.getUuid()));
            resultList.forEach(System.out::println);
        } catch (NoResultException ignore) {}
        catch (Exception e) {
            VKNews.getInstance().printWarn(e, "getSeenPlayerUuidList error");
        } finally {
            session.close();
        }

        return resultList;
    }

    private int getLastPostId() {
        int result = -1;
        Session session = sessionFactory.openSession();

        try {
            Query query = session.createQuery("FROM LastPostId");
            result = ((LastPostId) query.getSingleResult()).getLastPostId();
            System.out.println(result);
        } catch (NoResultException ignore) {}
        catch (Exception e) {
            VKNews.getInstance().printWarn(e, "getLastPostId error");
        } finally {
            session.close();
        }

        return result;
    }
}
