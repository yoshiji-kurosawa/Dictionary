package kurosawa.dictionary.main.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EIWA database table.
 * 
 */
@Entity
public class Eiwa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EIWA_ID_GENERATOR", sequenceName="EIWA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EIWA_ID_GENERATOR")
	private long id;

	private String english;

	private String japanese;

    public Eiwa() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnglish() {
		return this.english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getJapanese() {
		return this.japanese;
	}

	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}

}