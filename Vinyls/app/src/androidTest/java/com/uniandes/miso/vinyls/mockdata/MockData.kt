package com.uniandes.miso.vinyls.mockdata

import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.models.CollectorAlbum
import com.uniandes.miso.vinyls.models.Comment
import com.uniandes.miso.vinyls.models.FavoritePerformer

class MockData {

    fun collectorData(): List<Collector> = (1..25).map {
        Collector(it, "testname $it", "testphone $it,", "testemmail $it",
            listOf(
                Comment(
                    1,
                    "commentdesctest1",
                    rating = 5
                ), Comment(
                    2,
                    "commentdesctest2",
                    rating = 5
                )
            ),
            listOf(
                FavoritePerformer(1,  "nameperformertest1", "https://cdn-icons-png.flaticon.com/128/8579/8579786.png", "descriptiontest1", "05-05-1994"),
                FavoritePerformer(1,  "nameperformertest2", "https://cdn-icons-png.flaticon.com/128/8579/8579786.png", "descriptiontest2", "05-05-1994")
            ),
            listOf(
                CollectorAlbum(1, 1789, "available"),
                CollectorAlbum(1, 1789, "available")
            )
        )
    }
}