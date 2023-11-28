package com.example.schedule.cs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "customer")
@Getter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int arriveTime;
	private int useTime;
	private int startTime;
	private int waitTime;

	@Builder
	public Customer(String name, int arriveTime, int useTime) {
		this.name = name;
		this.arriveTime = arriveTime;
		this.useTime = useTime;
	}

	public void updateStartWaitTime(int startTime, int waitTime) {
		this.startTime = startTime;
		this.waitTime = waitTime;
	}
}
