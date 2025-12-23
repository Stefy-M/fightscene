package com.fightscene.backend.domain.fighter;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fightscene.backend.domain.gym.Gym;
import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.domain.video.Video;

@Entity
@Table(name = "fighters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fighter {
	
	@Id
	@Column ( name = "fighter_id", nullable = false, updatable = false)
	private UUID fighterId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", unique = true, nullable = false )
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name ="gym_id")
	private Gym gym;
	
	@Column(nullable = false, length = 255)
	private String firstName;
	
	@Column(nullable = false, length = 255)
	private String lastName;
	
	@Column(nullable = false, length = 255)
	private String nickname;
	
	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(nullable = false, length = 50, columnDefinition = "gender_enum")
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(name = "weight_class", nullable = false, length = 50, columnDefinition = "weight_class_enum")
	private WeightClass weightclass;
	
	@Column(columnDefinition= "TEXT")
	private String bio;
	
	@OneToMany(mappedBy = "fighter", fetch = FetchType.LAZY)
	private List<Video> videos = new ArrayList<>();
	
	@Column(name= "profile_pic_url", length = 500)
	private String profilePicUrl;
	
	@Column(name = "tapology_profile_url", length = 500)
	private String tapologyProfileUrl;
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "external_links")
	private Map<String, String> externalLinks = new HashMap<>();
	
	@Column(name = "storage_used_bytes", nullable = false)
	private long storageUsedBytes;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;
	
	@Column (name = "updated_at", nullable = false)
	private Instant updatedAt;
	
	@PrePersist
	private void prePersit() {
		if(this.fighterId == null) this.fighterId = UUID.randomUUID();
		Instant now = Instant.now();
		this.createdAt = now;
		this.updatedAt = now;
		if(this.externalLinks == null) this.externalLinks = new HashMap<>();
	}
	
	@PreUpdate
	private void preUpdate() {
		this.updatedAt = Instant.now();
	}

}
