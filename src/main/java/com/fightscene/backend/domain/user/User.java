package com.fightscene.backend.domain.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@Column(name = "user_id", nullable = false, updatable = false)
	private UUID userId;
	
	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;
	
	@Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;
	
	@Enumerated(EnumType.STRING)
	@Column (name= "role", nullable = false, length = 50)
	private UserRole role;
	
	@Column(name ="created_at", nullable = false, updatable = false)
	private Instant createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
	
	@PrePersist
	public void prePersist() {
		if(userId == null) userId = UUID.randomUUID();
		Instant now = Instant.now();
		createdAt = now;
		updatedAt = now;
		if(role == null) role = UserRole.USER;
	}
	
	@PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
