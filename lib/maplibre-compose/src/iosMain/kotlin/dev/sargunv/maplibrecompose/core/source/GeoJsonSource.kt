package dev.sargunv.maplibrecompose.core.source

import cocoapods.MapLibre.MLNShapeSource
import cocoapods.MapLibre.MLNShapeSourceOptionBuffer
import cocoapods.MapLibre.MLNShapeSourceOptionClusterProperties
import cocoapods.MapLibre.MLNShapeSourceOptionClusterRadius
import cocoapods.MapLibre.MLNShapeSourceOptionClustered
import cocoapods.MapLibre.MLNShapeSourceOptionLineDistanceMetrics
import cocoapods.MapLibre.MLNShapeSourceOptionMaximumZoomLevel
import cocoapods.MapLibre.MLNShapeSourceOptionMaximumZoomLevelForClustering
import cocoapods.MapLibre.MLNShapeSourceOptionSimplificationTolerance
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.Expression.Companion.const
import dev.sargunv.maplibrecompose.core.util.toMLNShape
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import io.github.dellisd.spatialk.geojson.GeoJson
import platform.Foundation.NSNumber
import platform.Foundation.NSURL

public actual class GeoJsonSource : Source {
  override val impl: MLNShapeSource

  public actual constructor(id: String, dataUrl: String, options: GeoJsonOptions) {
    impl =
      MLNShapeSource(
        identifier = id,
        URL = NSURL(string = dataUrl),
        options = buildOptionMap(options),
      )
  }

  public actual constructor(id: String, data: GeoJson, options: GeoJsonOptions) {
    impl =
      MLNShapeSource(identifier = id, shape = data.toMLNShape(), options = buildOptionMap(options))
  }

  private fun buildOptionMap(options: GeoJsonOptions) =
    buildMap<Any?, Any?> {
      put(MLNShapeSourceOptionMaximumZoomLevel, NSNumber(options.maxZoom))
      put(MLNShapeSourceOptionBuffer, NSNumber(options.buffer))
      put(MLNShapeSourceOptionLineDistanceMetrics, NSNumber(options.lineMetrics))
      put(MLNShapeSourceOptionSimplificationTolerance, NSNumber(options.tolerance.toDouble()))
      put(MLNShapeSourceOptionClustered, NSNumber(options.cluster))
      put(MLNShapeSourceOptionMaximumZoomLevelForClustering, NSNumber(options.clusterMaxZoom))
      put(MLNShapeSourceOptionClusterRadius, NSNumber(options.clusterRadius))
      put(
        MLNShapeSourceOptionClusterProperties,
        options.clusterProperties.mapValues { (_, p) ->
          Expression.ofList(listOf(const(p.operator), p.mapper)).toNSExpression()
        },
      )
    }

  public actual fun setDataUrl(url: String) {
    impl.setURL(NSURL(string = url))
  }

  public actual fun setData(geoJson: GeoJson) {
    impl.setShape(geoJson.toMLNShape())
  }
}
