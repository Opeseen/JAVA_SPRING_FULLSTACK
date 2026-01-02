package com.eazybytes.eazystore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONTACTS")
@NamedQuery(name = "Contact.findByStatus", query = "SELECT c FROM Contact c WHERE c.status = :status")
@NamedNativeQuery(name = "Contact.findByStatusWithNativeQuery",
    query = "SELECT * FROM Contact  WHERE status = :status", resultClass = Contact.class )
public class Contact extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CONTACT_ID", nullable = false)
  private Long contactId;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "EMAIL", nullable = false, length = 100)
  private String email;

  @Column(name = "MOBILE_NUMBER", nullable = false, length = 15)
  private String mobileNumber;

  @Column(name = "MESSAGE", nullable = false, length = 500)
  private String message;

  @Column(name = "STATUS", nullable = false, length = 50)
  private String status;

}