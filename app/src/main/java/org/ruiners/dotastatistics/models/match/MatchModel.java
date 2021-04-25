package org.ruiners.dotastatistics.models.match;

import org.ruiners.dotastatistics.models.hero.HeroModel;

public class MatchModel {
    public long match_id;
    public Integer player_slot;
    public boolean radiant_win;
    public Integer duration;
    public Integer game_mode;
    public Integer lobby_type;
    public Integer hero_id;
    public Integer start_time;
    public Integer version;
    public Integer kills;
    public Integer deaths;
    public Integer assists;
    public Integer skill;
    public Integer lane;
    public Integer lane_role;
    public Integer cluster;
    public Integer leaver_status;
    public Integer party_size;
    public HeroModel hero;
    public String mode;
}
