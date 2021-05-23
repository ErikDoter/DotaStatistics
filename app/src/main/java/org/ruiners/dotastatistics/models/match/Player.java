package org.ruiners.dotastatistics.models.match;

import org.ruiners.dotastatistics.models.hero.HeroModel;

public class Player {
    public Integer hero_id;
    public HeroModel hero;
    public Integer kills;
    public Integer deaths;
    public Integer assists;
    public String personaname;
    public Integer gold_per_min;
    public Integer xp_per_min;
    public boolean isRadiant;
}
