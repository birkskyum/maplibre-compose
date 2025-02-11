package dev.sargunv.maplibrecompose.core.layer

import androidx.compose.ui.graphics.Color
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.TResolvedImage
import dev.sargunv.maplibrecompose.core.util.toMLNExpression
import org.maplibre.android.style.layers.BackgroundLayer as MLNBackgroundLayer
import org.maplibre.android.style.layers.PropertyFactory

@PublishedApi
internal actual class BackgroundLayer actual constructor(id: String) : Layer() {

  override val impl: MLNBackgroundLayer = MLNBackgroundLayer(id)

  actual fun setBackgroundColor(color: Expression<Color>) {
    impl.setProperties(PropertyFactory.backgroundColor(color.toMLNExpression()))
  }

  actual fun setBackgroundPattern(pattern: Expression<TResolvedImage>) {
    impl.setProperties(PropertyFactory.backgroundPattern(pattern.toMLNExpression()))
  }

  actual fun setBackgroundOpacity(opacity: Expression<Number>) {
    impl.setProperties(PropertyFactory.backgroundOpacity(opacity.toMLNExpression()))
  }
}
