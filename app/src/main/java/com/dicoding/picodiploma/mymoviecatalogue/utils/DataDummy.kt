package com.dicoding.picodiploma.mymoviecatalogue.utils

import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.data.MovieEntity

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "m1",
                "Alita: Battle Angel",
                "Robert Rodriguez",
                "Action, Science Fiction, Adventure",
                "January 31, 2019",
                "71%",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                R.drawable.poster_alita
            )
        )
        movies.add(
            MovieEntity(
                "m2",
                "A Star Is Born",
                "William A. Wellman & Robert Carson",
                "Drama, Romance, Music",
                "October 3, 2018",
                "75%",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                R.drawable.poster_a_start_is_born
            )
        )
        movies.add(
            MovieEntity(
                "m3",
                "Aquaman",
                "James Wan",
                "Action, Adventure, Fantasy",
                "December 7, 2018",
                "69%",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                R.drawable.poster_aquaman
            )
        )
        movies.add(
            MovieEntity(
                "m4",
                "Bohemian Rhapsody",
                "Bryan Singer",
                "Music, Drama",
                "October 24, 2018",
                "80%",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                R.drawable.poster_bohemian
            )
        )
        movies.add(
            MovieEntity(
                "m5",
                "Cold Pursuit",
                "Hans Petter Moland",
                "Action, Crime, Thriller",
                "February 7, 2019",
                "56%",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                R.drawable.poster_cold_persuit
            )
        )
        movies.add(
            MovieEntity(
                "m6",
                "Creed",
                "Ryan Coogler",
                "Drama",
                "November 25, 2015",
                "74%",
                "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
                R.drawable.poster_creed
            )
        )
        movies.add(
            MovieEntity(
                "m7",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "David Yates",
                "Adventure, Fantasy, Drama",
                "November 14, 2018",
                "69%",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                R.drawable.poster_crimes
            )
        )
        movies.add(
            MovieEntity(
                "m8",
                "Glass",
                "M. Night Shyamalan",
                "Thriller, Drama, Science Fiction",
                "January 16, 2019",
                "66%",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                R.drawable.poster_glass
            )
        )
        movies.add(
            MovieEntity(
                "m9",
                "How to Train Your Dragon",
                "Dean DeBlois",
                "Fantasy, Adventure, Animation, Family",
                "March 10, 2010",
                "78%",
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
                R.drawable.poster_how_to_train
            )
        )
        movies.add(
            MovieEntity(
                "m10",
                "Avengers: Infinity War",
                "Anthony Russo",
                "Adventure, Action, Science Fiction",
                "April 25, 2018",
                "83%",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                R.drawable.poster_infinity_war
            )
        )

        return movies
    }

    fun generateDummyTvShows(): List<MovieEntity> {
        val tvShows = ArrayList<MovieEntity>()

        tvShows.add(
            MovieEntity(
                "t1",
                "Arrow",
                "Greg Berlanti",
                "Crime, Drama, Mystery, Action & Adventure",
                "October 10, 2012",
                "66%",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                R.drawable.poster_arrow
            )
        )
        tvShows.add(
            MovieEntity(
                "t2",
                "Doom Patrol",
                "Jeremy Carver",
                "Sci-Fi & Fantasy, Action & Adventure, Comedy",
                "February 15, 2019",
                "76%",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                R.drawable.poster_doom_patrol
            )
        )
        tvShows.add(
            MovieEntity(
                "t3",
                "Dragon Ball",
                "Akira Toriyama",
                "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
                "February 26, 1986",
                "80%",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                R.drawable.poster_dragon_ball
            )
        )
        tvShows.add(
            MovieEntity(
                "t4",
                "Family Guy",
                "Seth MacFarlane",
                "Animation, Comedy",
                "October 12, 2009",
                "77%",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                R.drawable.poster_family_guy
            )
        )
        tvShows.add(
            MovieEntity(
                "t5",
                "Fairy Tail",
                "Hiro Mashima",
                "Adventure, fantasy",
                "October 12, 2009",
                "77%",
                "In the mystical land of Fiore, magic exists as an essential part of everyday life. Countless magic guilds lie at the core of all magical activity, and serve as venues for like-minded mages to band together and take on job requests. Among them, Fairy Tail stands out from the rest as a place of strength, spirit, and family.",
                R.drawable.poster_fairytail
            )
        )
        tvShows.add(
            MovieEntity(
                "t6",
                "The Flash",
                "Greg Berlanti",
                "Drama, Sci-Fi & Fantasy",
                "October 7, 2014",
                "76%",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                R.drawable.poster_flash
            )
        )
        tvShows.add(
            MovieEntity(
                "t7",
                "Game of Thrones",
                "David Benioff",
                "Sci-Fi & Fantasy, Drama, Action & Adventure, Mystery",
                "April 17, 2011",
                "84%",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                R.drawable.poster_god
            )
        )
        tvShows.add(
            MovieEntity(
                "t8",
                "Gotham",
                "Bruno Heller",
                "Drama, Fantasy, Crime",
                "September 22, 2014",
                "75%",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                R.drawable.poster_gotham
            )
        )
        tvShows.add(
            MovieEntity(
                "t9",
                "Grey's Anatomy",
                "Shonda Rhimes",
                "Drama",
                "March 27, 2005",
                "82%",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                R.drawable.poster_grey_anatomy
            )
        )
        tvShows.add(
            MovieEntity(
                "t10",
                "Hanna",
                "Greg Berlanti",
                "Action & Adventure, Drama",
                "March 28, 2019",
                "74%",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                R.drawable.poster_hanna
            )
        )
        return tvShows
    }
}