package org.ruiners.dotastatistics.models.match;

import java.util.ArrayList;
import java.util.List;

public class AllMatchModel {
    public long match_id;
    public String dire_score;
    public Integer duration;
    public Integer game_mode;
    public Integer radiant_score;
    public boolean radiant_win;
    public String skill;
    public List<Player> players;
    public String mode;
    public String duration_all;
    public List<Player> radiants_players = new ArrayList<>();
    public List<Player> dire_players = new ArrayList<>();
    public String win;
}
