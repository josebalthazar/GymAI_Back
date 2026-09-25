package com.balthazar.GymAI_Backend.auth.provider.contract;

import com.balthazar.GymAI_Backend.auth.provider.credentials.AuthCredentials;
import com.balthazar.GymAI_Backend.user.entity.User;

public interface AuthProvider {

    User authenticate(AuthCredentials credentials);

}