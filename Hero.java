public class Hero extends Combatant {
        public String heroExperienceLevel;
        public int heroNumber;

    public void setHeroNumber(int heroNumber) {
            this.heroNumber = heroNumber;
        }
}

class Warrior extends Hero {
    String CombatantName = "Warrior";

}

class Hunter extends Hero {
    String CombatantName = "Hunter";
    public int HunterArrowsNumber = 15;
}

class Mage extends Hero {
    String CombatantName = "Mage";
    public int mageManaPoints = 10;
}

class Healer extends Hero {
    String CombatantName = "Healer";
    public int healerDefenseBonusPoints = 5;
}
