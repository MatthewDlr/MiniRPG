public class Hero extends Combatant {
        public String heroExperienceLevel;
        public int heroNumber;

    public void setHeroNumber(int heroNumber) {
            this.heroNumber = heroNumber;
        }
}

class Warrior extends Hero  {
    public String talent = "Last Power";
    public String talentDescription = "When his life is under 25%, his attack is increased by 20%";

        


}

class Hunter extends Hero {
    public int HunterArrowsNumber = 10;
    public String talent = "Brave Bow";
    public String talentDescription = "When his life is full, arrows are not used ";
}


class Mage extends Hero {
    public int souls = 10;
    public String talent = "Soul gathering";
    public String talentDescription = "Damaging enemies produce a small amount of souls";

}

class Healer extends Hero {
    public String talent = "Self Help";
    public String talentDescription = "Life is increased by 5% if no damages are received";
}
