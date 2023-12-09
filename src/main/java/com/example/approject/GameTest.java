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
    public void Test1() {
        Assert.assertFalse(game.isIsFlipped());
    }
    @Test
    public void Test2(){
        Player player=Player.getPlayer();
        Assert.assertNotNull(player);
    }
    @Test
    public void Test3(){
        Assert.assertEquals(0,game.getCurrentScore());
    }
    @Test
    public void Test4(){
        Assert.assertEquals(0,game.getCherryCount());
    }
    @Test
    public void Test5(){
        Scoreboard scoreboard=new Scoreboard("red","bold");
        Assert.assertNotNull(scoreboard);
    }
}
