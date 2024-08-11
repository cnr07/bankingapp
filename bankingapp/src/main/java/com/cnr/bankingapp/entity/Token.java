package com.cnr.bankingapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "token", schema="banking-app")
@Data
@Schema(name = "Token", description = "Token entity")
public class Token {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	@Schema(name = "id", description = "Token id", example = "1")
    private Integer id;

    @Column(name = "access_token")
    @Schema(name = "accessToken", description = "Access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjbnIiLCJpYXQiOjE3MjMzOTU4MjUsImV4cCI6MTcyMzM5OTQyNX0.FdIe2rWtSVqKztjv4HVmryXeEHlmqC-5P6EhtNVPaxo")
    private String accessToken;

    @Column(name = "refresh_token")
    @Schema(name = "refreshToken", description = "Refresh token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjbnIiLCJpYXQiOjE3MjMzOTU4MjUsImV4cCI6MTcyMzM5OTQyNX0.FdIe2rWtSVqKztjv4HVmryXeEHlmqC-5P6EhtNVPaxo")
    private String refreshToken;

    @Column(name = "is_logged_out")
    @Schema(name = "loggedOut", description = "Tokens user logged out", example = "1")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Schema(name = "user", description = "Tokens user ")
    private User user;


    


}
