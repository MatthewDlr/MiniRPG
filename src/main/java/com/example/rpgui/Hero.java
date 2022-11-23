package com.example.rpgui;

public abstract class Hero extends Combatant {
        public String talent;
        public  String talentDescription;

}

class Warrior extends Hero  {

}

class Hunter extends Hero {

    public int arrowsNumber ;
}


class Mage extends Hero {
    public int numberOfSouls ;

}

class Healer extends Hero {
    public int currentLifePoints;


}
