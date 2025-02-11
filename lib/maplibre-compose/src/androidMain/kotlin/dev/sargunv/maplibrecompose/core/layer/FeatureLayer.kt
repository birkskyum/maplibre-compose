package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.source.Source

@PublishedApi
internal actual sealed class FeatureLayer actual constructor(actual val source: Source) : Layer() {
  actual abstract var sourceLayer: String

  actual abstract fun setFilter(filter: Expression<Boolean>)
}
