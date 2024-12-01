package dev.sargunv.maplibrecompose.core.layer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import cocoapods.MapLibre.MLNCircleStyleLayer
import dev.sargunv.maplibrecompose.core.expression.CirclePitchAlignment
import dev.sargunv.maplibrecompose.core.expression.CirclePitchScale
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.TranslateAnchor
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate

@PublishedApi
internal actual class CircleLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNCircleStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: Expression<Boolean>) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setCircleSortKey(sortKey: Expression<Number>) {
    impl.circleSortKey = sortKey.toNSExpression()
  }

  actual fun setCircleRadius(radius: Expression<Dp>) {
    impl.circleRadius = radius.toNSExpression()
  }

  actual fun setCircleColor(color: Expression<Color>) {
    impl.circleColor = color.toNSExpression()
  }

  actual fun setCircleBlur(blur: Expression<Number>) {
    impl.circleBlur = blur.toNSExpression()
  }

  actual fun setCircleOpacity(opacity: Expression<Number>) {
    impl.circleOpacity = opacity.toNSExpression()
  }

  actual fun setCircleTranslate(translate: Expression<DpOffset>) {
    impl.circleTranslation = translate.toNSExpression()
  }

  actual fun setCircleTranslateAnchor(translateAnchor: Expression<TranslateAnchor>) {
    impl.circleTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setCirclePitchScale(pitchScale: Expression<CirclePitchScale>) {
    impl.circleScaleAlignment = pitchScale.toNSExpression()
  }

  actual fun setCirclePitchAlignment(pitchAlignment: Expression<CirclePitchAlignment>) {
    impl.circlePitchAlignment = pitchAlignment.toNSExpression()
  }

  actual fun setCircleStrokeWidth(strokeWidth: Expression<Dp>) {
    impl.circleStrokeWidth = strokeWidth.toNSExpression()
  }

  actual fun setCircleStrokeColor(strokeColor: Expression<Color>) {
    impl.circleStrokeColor = strokeColor.toNSExpression()
  }

  actual fun setCircleStrokeOpacity(strokeOpacity: Expression<Number>) {
    impl.circleStrokeOpacity = strokeOpacity.toNSExpression()
  }
}
