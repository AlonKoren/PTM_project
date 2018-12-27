package Matrices;

public enum Direction 
{
    Right("Right"),
    Left("Left"),
    Down("Down"),
    Up("Up");

    private String direction;
    Direction(String direction) {
        this.direction=direction;
    }

    public String getDirection() {
        return direction;
    }
}
