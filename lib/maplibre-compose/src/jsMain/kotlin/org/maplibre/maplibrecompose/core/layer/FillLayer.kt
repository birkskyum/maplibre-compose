package org.maplibre.maplibrecompose.core.layer

import org.maplibre.maplibrecompose.core.source.Source
import org.maplibre.maplibrecompose.expressions.ast.CompiledExpression
import org.maplibre.maplibrecompose.expressions.value.BooleanValue
import org.maplibre.maplibrecompose.expressions.value.ColorValue
import org.maplibre.maplibrecompose.expressions.value.DpOffsetValue
import org.maplibre.maplibrecompose.expressions.value.FloatValue
import org.maplibre.maplibrecompose.expressions.value.ImageValue
import org.maplibre.maplibrecompose.expressions.value.TranslateAnchor

internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setFillSortKey(sortKey: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setFillAntialias(antialias: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setFillOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setFillColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setFillOutlineColor(outlineColor: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setFillTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setFillTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setFillPattern(pattern: CompiledExpression<ImageValue>) {
    TODO()
  }
}
