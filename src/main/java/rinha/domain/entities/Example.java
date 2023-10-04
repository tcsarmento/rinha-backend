package rinha.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "BPM_PROCESS_VERSION")
@DynamicUpdate(true)
public class Example implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDT_BPM_PROCESS_TYPE")
	@SequenceGenerator(name = "PROCESS_SEQUENCE", sequenceName = "SQ_BPMPROCVERS_IDT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESS_SEQUENCE")
	private Long id;

	@Column(name = "PROCESS_KEY", nullable = false)
	private String processKey;
		
	@Column(name = "DES_BPM_PROCESS_VERSION", nullable = false)
	private String description;
	
	@Column(name = "NAM_IM_QUEUE", nullable = false)
	private String responsibleQueue;
	
	@Column(name = "NAM_BUSINESS_PROCESS", nullable = false)
	private String businessProcessName;
	
	@Column(name = "DES_BUSINESS_PROCESS", nullable = false)
	private String businessProcessDescription;
	
	@Column(name = "FLG_PROCESS_ONLINE")
    private Boolean online;
	
	@Column(name = "FLG_PROCESS_ACTIVE")
    private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponsibleQueue() {
		return responsibleQueue;
	}

	public void setResponsibleQueue(String responsibleQueue) {
		this.responsibleQueue = responsibleQueue;
	}

	public String getBusinessProcessName() {
		return businessProcessName;
	}

	public void setBusinessProcessName(String businessProcessName) {
		this.businessProcessName = businessProcessName;
	}

	public String getBusinessProcessDescription() {
		return businessProcessDescription;
	}

	public void setBusinessProcessDescription(String businessProcessDescription) {
		this.businessProcessDescription = businessProcessDescription;
	}

	public Boolean isOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}