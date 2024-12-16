
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {

        Album album = new Album("Album", "AC/DC");

        album.addSong("TNT", 4.50);
        album.addSong("Highway to Hell", 5.50);
        album.addSong("Thunderstruck", 4.20);

        albums.add(album);

        album = new Album("Album2", "Eminem");

        album.addSong("Rap God", 6.10);
        album.addSong("Not Afraid", 5.50);
        album.addSong("Lose Yourself", 6.20);

        albums.add(album);

        LinkedList<Songs> playlist_1 = new LinkedList<>();

        albums.get(0).addToPlayList("TNT", playlist_1);
        albums.get(0).addToPlayList("Highway to Hell", playlist_1);
        albums.get(1).addToPlayList("Rap God", playlist_1);

        play(playlist_1);
    }

    private static void play(LinkedList<Songs> playlist) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Songs> listIterator = playlist.listIterator();

        if (playlist.isEmpty()) {
            System.out.println("This playlist has no songs.");
            return;
        } else {
            System.out.println("Now playing: " + listIterator.next());
            printMenu();
        }

        while (!quit) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;

                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing: " + listIterator.next());
                    } else {
                        System.out.println("No more songs available. Reached the end of the playlist.");
                        forward = false;
                    }
                    break;

                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing: " + listIterator.previous());
                    } else {
                        System.out.println("We are at the start of the playlist.");
                    }
                    break;

                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Replaying: " + listIterator.previous());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the playlist.");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Replaying: " + listIterator.next());
                            forward = true;
                        } else {
                            System.out.println("We are at the end of the playlist.");
                        }
                    }
                    break;

                case 4:
                    printList(playlist);
                    break;

                case 5:
                    printMenu();
                    break;

                case 6:
                    if (playlist.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing: " + listIterator.next());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing: " + listIterator.previous());
                        } else {
                            System.out.println("Playlist is now empty.");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - to quit\n"
                + "1 - to play the next song\n"
                + "2 - to play the previous song\n"
                + "3 - to replay the current song\n"
                + "4 - list songs in the playlist\n"
                + "5 - print menu options\n"
                + "6 - delete the current song from the playlist");
    }

    private static void printList(LinkedList<Songs> playlist) {
        Iterator<Songs> iterator = playlist.iterator();
        System.out.println("-----------");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("-----------");
    }
}
