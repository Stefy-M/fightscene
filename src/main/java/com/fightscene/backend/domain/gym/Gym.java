package com.fightscene.backend.domain.gym;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fightscene.backend.domain.fighter.Fighter;


@Entity
@Table(name ="gyms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gym {
	
	@Id
	@Column ( name ="gym_id", nullable = false, updatable = false)
	private UUID gymId;
	
	@Column (name = "name" ,nullable = false, length = 255)
	private String name;
	
	@Column ( name = "location", length = 255)
	private String location;
	
	@OneToMany(mappedBy = "gym", fetch = FetchType.LAZY)
	private List<Fighter> fighters;
	
	@Column (name = "created_At", nullable = false, updatable = false)
	private Instant createdAt;
	
	@Column (name = "updated_At", nullable = false)
	private Instant updatedAt;
	
	@PrePersist
	private void prePersist() {
		if( this.gymId == null) this.gymId = UUID.randomUUID();
		Instant now = Instant.now();
		this.createdAt = now;
		this.updatedAt = now;
		
	}
	
	@PreUpdate
	private void preUpdate() {
		this.updatedAt = Instant.now();
	}
	
}
