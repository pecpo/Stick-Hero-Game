package com.example.approject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {
    public static Game game=null;
    @BeforeClass
    public static void createGame(){
        game=new Game();
    }
    @Test
    public void Test1() { //checks if character is flipped initially
        Assert.assertFalse(game.isIsFlipped());
    }
    @Test
    public void Test2(){ //checks our singleton design pattern
        Player player=Player.getPlayer();
        Assert.assertNotNull(player);
    }
    @Test
    public void Test3(){ //check if initial score of character is zero
        Assert.assertEquals(0,game.getCurrentScore());
    }
    @Test
    public void Test4(){ //check if initial score of character is zero
        Assert.assertEquals(0,game.getCurrentScore());
    }
}
