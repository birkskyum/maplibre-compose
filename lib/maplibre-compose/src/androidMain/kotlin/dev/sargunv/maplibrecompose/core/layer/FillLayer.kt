package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.TranslateAnchor
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toMLNExpression
import org.maplibre.android.style.expressions.Expression as MLNExpression
import org.maplibre.android.style.layers.FillLayer as MLNFillLayer
import org.maplibre.android.style.layers.PropertyFactory

@PublishedApi
internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = MLNFillLayer(id, source.id)

  actual override var sourceLayer: String by impl::sourceLayer

  actual override fun setFilter(filter: Expression.Boolean) {
    impl.setFilter(filter.toMLNExpression() ?: MLNExpression.literal(true))
  }

  actual fun setFillSortKey(sortKey: Expression.Float) {
    impl.setProperties(PropertyFactory.fillSortKey(sortKey.toMLNExpression()))
  }

  actual fun setFillAntialias(antialias: Expression.Boolean) {
    impl.setProperties(PropertyFactory.fillAntialias(antialias.toMLNExpression()))
  }

  actual fun setFillOpacity(opacity: Expression.Float) {
    impl.setProperties(PropertyFactory.fillOpacity(opacity.toMLNExpression()))
  }

  actual fun setFillColor(color: Expression.Color) {
    impl.setProperties(PropertyFactory.fillColor(color.toMLNExpression()))
  }

  actual fun setFillOutlineColor(outlineColor: Expression.Color) {
    impl.setProperties(PropertyFactory.fillOutlineColor(outlineColor.toMLNExpression()))
  }

  actual fun setFillTranslate(translate: Expression.DpOffset) {
    impl.setProperties(PropertyFactory.fillTranslate(translate.toMLNExpression()))
  }

  actual fun setFillTranslateAnchor(translateAnchor: Expression.Enum<TranslateAnchor>) {
    impl.setProperties(PropertyFactory.fillTranslateAnchor(translateAnchor.toMLNExpression()))
  }

  actual fun setFillPattern(pattern: Expression.ResolvedImage) {
    impl.setProperties(PropertyFactory.fillPattern(pattern.toMLNExpression()))
  }
}
