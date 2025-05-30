package dev.sargunv.maplibrecompose.compose

import dev.sargunv.maplibrecompose.compose.offline.TilePackDefinition
import dev.sargunv.maplibrecompose.compose.offline.toMLNOfflineRegion
import dev.sargunv.maplibrecompose.compose.offline.toTilePackDefinition
import io.github.dellisd.spatialk.geojson.BoundingBox
import io.github.dellisd.spatialk.geojson.Polygon
import io.github.dellisd.spatialk.geojson.Position
import kotlin.experimental.ExperimentalNativeApi
import kotlin.test.Test
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
class TilePackDefinitionTest {
  @Test
  fun convertTilePyramid() {
    val noMax =
      TilePackDefinition.TilePyramid(
        styleUrl = "https://example.com",
        bounds =
          BoundingBox(
            southwest = Position(longitude = -10.0, latitude = -11.0),
            northeast = Position(longitude = 12.0, latitude = 13.0),
          ),
        minZoom = 3,
        maxZoom = null, // infinity
      )
    assert(noMax.toMLNOfflineRegion().toTilePackDefinition() == noMax)

    val minMax =
      TilePackDefinition.TilePyramid(
        styleUrl = "https://example.com",
        bounds =
          BoundingBox(
            southwest = Position(longitude = -10.0, latitude = -11.0),
            northeast = Position(longitude = 12.0, latitude = 13.0),
          ),
        minZoom = 3,
        maxZoom = 10,
      )
    assert(minMax.toMLNOfflineRegion().toTilePackDefinition() == minMax)
  }

  @Test
  fun convertShape() {
    val noMax =
      TilePackDefinition.Shape(
        styleUrl = "https://example.com",
        shape =
          Polygon(
            listOf(
              Position(longitude = -10.0, latitude = -11.0), // southwest
              Position(longitude = -10.0, latitude = 13.0), // northwest
              Position(longitude = 12.0, latitude = 13.0), // northeast
              Position(longitude = 12.0, latitude = -11.0), // southeast
            )
          ),
        minZoom = 3,
        maxZoom = null, // infinity
      )
    assert(noMax.toMLNOfflineRegion().toTilePackDefinition() == noMax)

    val minMax =
      TilePackDefinition.Shape(
        styleUrl = "https://example.com",
        shape =
          Polygon(
            listOf(
              Position(longitude = -10.0, latitude = -11.0),
              Position(longitude = -10.0, latitude = 13.0),
              Position(longitude = 12.0, latitude = 13.0),
              Position(longitude = 12.0, latitude = -11.0),
            )
          ),
        minZoom = 3,
        maxZoom = 10,
      )
    assert(minMax.toMLNOfflineRegion().toTilePackDefinition() == minMax)
  }
}
