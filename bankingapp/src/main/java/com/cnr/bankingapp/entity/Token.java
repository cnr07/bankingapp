package com.cnr.bankingapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "token", schema="banking-app")
@Data
public class Token {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    


}
