package dev.sargunv.maplibrecompose.core.layer

@PublishedApi
internal expect sealed class Layer {
  val id: String
  var minZoom: Float
  var maxZoom: Float
  var visible: Boolean
}
