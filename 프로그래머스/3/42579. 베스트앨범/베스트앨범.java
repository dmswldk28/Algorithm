import java.util.*;

class Solution {
    static class Song {
        int id;
        int play;

        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlaySum = new HashMap<>();
        Map<String, List<Song>> genreSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genrePlaySum.put(genre, genrePlaySum.getOrDefault(genre, 0) + play);
            
            if (!genreSongs.containsKey(genre)) {
                genreSongs.put(genre, new ArrayList<>());
            }
            genreSongs.get(genre).add(new Song(i, play));
        }

        List<String> sortedGenres = new ArrayList<>(genrePlaySum.keySet());
        Collections.sort(sortedGenres, (a, b) -> genrePlaySum.get(b) - genrePlaySum.get(a));

        List<Integer> bestAlbum = new ArrayList<>();

        for (String genre : sortedGenres) {
            List<Song> songs = genreSongs.get(genre);

            Collections.sort(songs, (a, b) -> {
                if (a.play == b.play) return a.id - b.id;
                return b.play - a.play;
            });

            for (int i = 0; i < Math.min(songs.size(), 2); i++) {
                bestAlbum.add(songs.get(i).id);
            }
        }

        return bestAlbum.stream().mapToInt(i -> i).toArray();
    }
}