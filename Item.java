abstract class Item {
    public String itemName;
    public String itemDescription;
}

class Weapon extends Item {
    public int weaponAttack;
    public int weaponRange;
}

abstract class Consumable extends Item{
    public int ConsumableLifePointsBonus;
}

class Food extends Consumable {
}

class Potion extends Consumable {

}