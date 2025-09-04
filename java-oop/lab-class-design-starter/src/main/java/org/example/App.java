package org.example;

import org.example.controller.MainMenuController;
import org.example.repo.AlbumRepo;
import org.example.repo.ArtistRepo;
import org.example.repo.factory.AlbumRepoFactory;
import org.example.repo.factory.ArtistRepoFactory;
import org.example.view.ConsoleIO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConsoleIO io = new ConsoleIO();
        AlbumRepo albums = AlbumRepoFactory.instance();
        ArtistRepo artists = ArtistRepoFactory.instance();

        MainMenuController mainMenu = new MainMenuController(io, albums, artists);
        mainMenu.run();



    }
}
