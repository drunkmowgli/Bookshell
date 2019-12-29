package org.asm.labs.model;


import javax.persistence.*;

@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "genre_name")
	private String genreName;

	public Genre() {
	}

	public Genre(long id, String genreName) {
		this.id = id;
		this.genreName = genreName;
	}

	public long getId() {
		return id;
	}

	public String getGenreName() {
		return genreName;
	}

	@Override
	public String toString() {
		return "Genre{" +
				"id=" + id +
				", genreName='" + genreName + '\'' +
				'}';
	}
}
