import java.util.*;

public class Attack {

}

final class WarriorAttacks extends Attack{
    //Attack Model :                                                  Name         , Description                    , Damage, Targets nb, Defense boost, Atk boost, Speed boost
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Sword cut"   , "Attack the enemy with a sword", 40    ,  1        , 1            , 1        , 1           ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Swords Dance", "Highly Increase the attack"   , 0     ,  0        , 1            , 1.2      , 1           ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Wide Strike" , "Attack all the enemies"       , 10    ,  5        , 1            , 1        , 1           ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("MAX Sword"   , "Attack with the maximum power", 60    ,  1        , 0.8          , 0.8      , 0.8         ));
    //                                                                                                                                  ------------  1 = no change ------------
    //                                                                                                                                  ------------  < 1 = decrease -----------
    //                                                                                                                                  ------------  > 1 = increase -----------
}

final class HunterAttacks extends Attack{
    //Attack Model :                                                 Name             ,Description                     , Damage, Targets nb, Arrow cost, Priority?, Defense boost, Atk boost, Speed boost
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Quick Attack"   , "Always attack in first"       , 35    ,  1        , 0         , 1        , 1            , 1        , 1           ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("X Bow"          , "Attack using the bow "        , 50    ,  1        , 1         , 0        , 1            , 1        , 1           ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Arrow Rain"     , "toutch multiple target"       , 20    ,  2        , 2         , 0        , 1            , 1        , 1           ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Hone Caws"      , "Increase speed and attack"    , 0     ,  0        , 0         , 0        , 1            , 1.1      , 1.1         ));
    //                                                                                                                                                 | 1 = yes  | ------------  1 = no change ------------
    //                                                                                                                                                 | 0 = no   | ------------  < 1 = decrease -----------
    //                                                                                                                                                            | ------------  > 1 = increase -----------
}
    
final class MageAttacks extends Attack{
    //Attack Model :                                                 Name            ,Description                         , Damage, Targets nb, Soul cost, Soul Boost, Defense boost, Atk boost, Speed boost
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Shadow Sneak"  , "Attack from behind"               , 30   ,  1         , 0        , 0        , 1             ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Souls focus"   , "Focus on gathering more souls"    , 0    ,  0         , 0        , 1.1      , 1             ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Souls power"   , "Increase the defense of the team" , 0    ,  4         , 20       , 0        , 1.15          ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Final Shout"   , "Gathering all the souls to attack", "-"  ,  1         , 999      , 0        , 1             ));
    //                                                                                                                                                   | ---- < 1 = decrease ----
    //                                                                                                                                                   | ---- > 1 = increase ----
}

final class HealerAttacks extends Attack{
    //Attack Model :                                                 Name           ,Description                         , Damage, Targets nb , Heal
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Charge"       , "Basically charge on the enemy"    , 30    ,  1         , 0    ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Protect"      , "Protect from damages"             , 0     ,  0         , 0    ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Giga Drain"   , "Convert enemy life to heal"       , 20    ,  1         , "-"  ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Heal Wave"    , "Heal the team a little "          , 0     ,  4         , 15   ));
}