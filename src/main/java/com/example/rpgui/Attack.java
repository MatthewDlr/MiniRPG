package com.example.rpgui;
import java.util.*;

public class Attack {

}

final class WarriorAttacks extends Attack{
    //Attack Model :                                                  Name         , Description                                                                               , Damage, Targets nb, Defense change, Atk change, Speed change
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Sword cut"   , "Attack the enemy with the sword"                                                         , 40    ,  1        , 1d            , 1d        , 1d           ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Swords Dance", "Highly increase the attack of the Warrior"                                               , 0     ,  0        , 1d            , 1.2d      , 1d           ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Wide Strike" , "Attack all the enemies at once"                                                          , 10    ,  5        , 1d            , 1d        , 1d           ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Close Combat", "Rally all the Warrior power to attack, decrease the attack, defense and speed in return.", 60    ,  1        , 0.9d          , 0.9d      , 0.9         ));
    //                                                                                                                                                                                              ------------  1 = no change ------------
    //                                                                                                                                                                                              ------------  < 1 = decrease -----------
    //                                                                                                                                                                                              ------------  > 1 = increase -----------
}

final class HunterAttacks extends Attack{
    //Attack Model :                                                 Name             ,Description                                     , Damage, Targets nb, Arrow cost, Defense change, Atk change, Speed change
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Quick Attack"   , "Basic attack without arrows needed"           , 35    ,  1        , 0         , 1d            , 1d        , 1d           ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("X Bow"          , "Shoot a big arrow to an enemy "               , 50    ,  1        , 1         , 1d            , 1d        , 1d           ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Arrow Rain"     , "Shoot many arrows to touch multiple targets"  , 20    ,  5        , 2         , 1d            , 1d        , 1d           ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Hone Caws"      , "Increase speed and attack of the Hunter"      , 0     ,  0        , 0         , 1d            , 1.1d      , 1.1d         ));
    //                                                                                                                                                                 | ------------  1 = no change ------------
    //                                                                                                                                                                 | ------------  < 1 = decrease -----------
    //                                                                                                                                                                 | ------------  > 1 = increase -----------
}
    
final class MageAttacks extends Attack{
    //Attack Model :                                                 Name            ,Description                                                                          , Damage, Targets nb, Soul cost, Soul change, Defense change
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Shadow Sneak"  , "Attack an Enemy from behind"                                                       , 40   ,  1         , 0        , 1d         , 1d             ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Souls focus"   , "Focus on increasing the current number of souls"                                   , 0    ,  0         , 0        , 1.20d      , 1d             ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Souls Guard"   , "Increase the defense of the team"                                                  , 0    ,  4         , 20       , 1d         , 1.20d          ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Final Shout"   , "Gathering all the souls to attack with (the damages depend of the number of souls)", 99   ,  1         , 999      , 1d         , 1d             ));
    //                                                                                                                                                                                                    | ---- < 1 = decrease ----
    //                                                                                                                                                                                                    | ---- > 1 = increase ----
}

final class HealerAttacks extends Attack{
    //Attack Model :                                                 Name           ,Description                                                     , Damage , Targets nb , Heal
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Charge"       , "Basically charge on the enemy"                                , 30     ,  1         , 0    ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Protect"      , "Protect the healer from received damages"                     , 0      ,  0         , 0    ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Giga Drain"   , "Attack an enemy and convert a small part of damages to heal"  , 20     ,  1         , 0  ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Heal Wave"    , "Heal the team a little "                                      , 0      ,  4         , 15   ));
}

final class EnemyAttacks extends Attack{
    //                                                               Name               ,Damage , Targets nb , Opponent Defense change , Attack change, Opponent Speed change
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Charge"      ,"" , 30   , 1           , 1d                      , 1d            , 1d                    ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Creaking"    ,"", 0     , 5           , 0.95d                   , 1d            , 0.90d                 ));
    static ArrayList<Object> attack2b = new ArrayList<>(Arrays.asList("Stick Web"  ,"", 15    , 1           , 1d                      , 1d            , 0.90d                 ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Work Up"     ,"", 0     , 0           , 1d                      , 1.15d         , 1d                    ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Fury Attack" ,"", 15    , 1           , 1d                      , 1d            , 1d                    ));

}

final class BossAttacks extends Attack{
    //                                                               Name                 ,Damage , Targets nb , Accuracy, Defense change , Attack change, Oponent Speed change, Healing
    static ArrayList<Object> attack1 = new ArrayList<>(Arrays.asList("Hammer Charge"  ,"" , 70    , 1          , 75      , 1d             , 1d           , 1d                  , 0    ));
    static ArrayList<Object> attack2 = new ArrayList<>(Arrays.asList("Low Kick"       ,"" , 25    , 5          , 100     , 1d             , 1d           , 0.9d                , 0    ));
    static ArrayList<Object> attack3 = new ArrayList<>(Arrays.asList("Roost"          ,"" , 0     , 0          , 100     , 0.9            , 1d           , 1d                  , 1.25 ));
    static ArrayList<Object> attack4 = new ArrayList<>(Arrays.asList("Thunder Wave"   ,"" , 0     , 1          , 90      , 1              , 1d           , 1d                  , 1    ));
}