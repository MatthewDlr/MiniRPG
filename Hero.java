public abstract class Hero extends Combatant {
        static public String heroExperienceLevel;
        public String talent;
        public  String talentDescription;
}

class Warrior extends Hero  {

}

class Hunter extends Hero {
    public int maximumLifePoints;
    public int arrowsNumber ;
}


class Mage extends Hero {
    public int numberOfSouls ;



}

class Healer extends Hero {

}
