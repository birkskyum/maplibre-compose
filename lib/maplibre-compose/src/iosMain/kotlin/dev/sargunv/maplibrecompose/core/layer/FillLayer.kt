package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNFillStyleLayer
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.TranslateAnchor
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate

@PublishedApi
internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNFillStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: Expression.Boolean) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setFillSortKey(sortKey: Expression.Number) {
    impl.fillSortKey = sortKey.toNSExpression()
  }

  actual fun setFillAntialias(antialias: Expression.Boolean) {
    impl.fillAntialiased = antialias.toNSExpression()
  }

  actual fun setFillOpacity(opacity: Expression.Number) {
    impl.fillOpacity = opacity.toNSExpression()
  }

  actual fun setFillColor(color: Expression.Color) {
    impl.fillColor = color.toNSExpression()
  }

  actual fun setFillOutlineColor(outlineColor: Expression.Color) {
    impl.fillOutlineColor = outlineColor.toNSExpression()
  }

  actual fun setFillTranslate(translate: Expression.DpOffset) {
    impl.fillTranslation = translate.toNSExpression()
  }

  actual fun setFillTranslateAnchor(translateAnchor: Expression.Enum<TranslateAnchor>) {
    impl.fillTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setFillPattern(pattern: Expression.ResolvedImage) {
    // TODO: figure out how to unset a pattern in iOS
    if (pattern.value != null) {
      impl.fillPattern = pattern.toNSExpression()
    }
  }
}
