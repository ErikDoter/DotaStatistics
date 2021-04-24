package org.ruiners.dotastatistics;

import org.ruiners.dotastatistics.models.profile.ProfileModel;

public interface ProfileRepository {
    ProfileModel getProfile(String id);
}
