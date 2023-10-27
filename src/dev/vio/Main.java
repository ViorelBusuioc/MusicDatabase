package dev.vio;

import dev.vio.model.Artist;
import dev.vio.model.DataSource;
import dev.vio.model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();
        if(!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_NONE);
        if(artists == null) {
            System.out.println("No artists in the list");
            return;
        }

        for (var artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name: " + artist.getName());
        }

        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Heartless", DataSource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        for (SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());

        }

        dataSource.querySongsMetadata();
        int count = dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number of songs is = " + count);

        dataSource.createViewForSongArtists();

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter a song title: ");
//        String title = scanner.nextLine();
//
//        songArtists = dataSource.querySongInfoView(title);
//        if(songArtists.isEmpty()) {
//            System.out.println("Couldn't find the artist for the song");
//            return;
//        }
//
//        for (SongArtist artist : songArtists) {
//            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
//                    " Album name = " + artist.getAlbumName() +
//                    " Track number = " + artist.getTrack());
//        }

//        dataSource.insertSong("Touch of Grey", "Gratefull Dead", "In The Dark", 1);
//        dataSource.insertSong("Cine are noroc are", "Nicolae Guta", "Vara Manelelor", 1);

        dataSource.close();
    }
}
