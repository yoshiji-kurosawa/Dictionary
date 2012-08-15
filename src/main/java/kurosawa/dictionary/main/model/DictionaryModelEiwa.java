package kurosawa.dictionary.main.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.springframework.transaction.annotation.Transactional;

import kurosawa.dictionary.main.server.DictionaryModel;
import kurosawa.dictionary.main.server.DictionaryModelResponse;

public class DictionaryModelEiwa implements DictionaryModel {

	private Log log = LogFactory.getLog(DictionaryModelEiwa.class);
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		log.info("em:" + em);
		this.em = em;
	}

	@Transactional
	public DictionaryModelResponse search(String english) {
		log.info("english:" + english);
		TypedQuery<Eiwa> query = em.createQuery(
				"SELECT e FROM Eiwa AS e WHERE e.english = :english",
				Eiwa.class);
		query.setParameter("english", english);
		List<Eiwa> listEiwa = query.getResultList();
		DictionaryModelResponse dmr;
		if (listEiwa.size() == 0) {
			dmr = new DictionaryModelResponse("search", "検索失敗　見つかりません", english, "");
		} else if (listEiwa.size() == 1) {
			dmr = new DictionaryModelResponse("search", "検索成功", english, listEiwa.get(0).getJapanese());
		} else {
			dmr = new DictionaryModelResponse("search", "検索失敗　複数あります", english, "");
		}
		return dmr;
	}

	@Transactional
	public DictionaryModelResponse add(String english, String japanese) {
		log.info("english:" + english);
		log.info("japanese:" + japanese);
		Eiwa eiwa = new Eiwa();
		eiwa.setEnglish(english);
		eiwa.setJapanese(japanese);
		DictionaryModelResponse dmr;
		em.persist(eiwa);
		dmr = new DictionaryModelResponse("add", "追加成功", english, japanese);
		return dmr;
	}

	@Transactional
	public DictionaryModelResponse delete(String english) {
		log.info("english:" + english);
		TypedQuery<Eiwa> query = em.createQuery(
				"SELECT e FROM Eiwa AS e WHERE e.english = :english",
				Eiwa.class);
		query.setParameter("english", english);
		List<Eiwa> listEiwa = query.getResultList();
		DictionaryModelResponse dmr;
		if (listEiwa.size() == 0) {
			dmr = new DictionaryModelResponse("delete", "削除失敗　見つかりません", english, "");
		} else if (listEiwa.size() == 1) {
			em.remove(listEiwa.get(0));
			dmr = new DictionaryModelResponse("delete", "削除成功", english, listEiwa.get(0).getJapanese());
		} else {
			dmr = new DictionaryModelResponse("delete", "削除失敗　複数あります", english, "");
		}
		return dmr;
	}

}
