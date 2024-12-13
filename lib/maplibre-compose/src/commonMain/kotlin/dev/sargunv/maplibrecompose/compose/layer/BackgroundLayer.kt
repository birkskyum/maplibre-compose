package dev.sargunv.maplibrecompose.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.Color
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.Expressions.const
import dev.sargunv.maplibrecompose.core.expression.Expressions.nil
import dev.sargunv.maplibrecompose.core.layer.BackgroundLayer

/**
 * The background layer just draws the map background, by default, plain black.
 *
 * @param id Unique layer name.
 * @param minZoom The minimum zoom level for the layer. At zoom levels less than this, the layer
 *   will be hidden. A value in the range of `[0..24]`.
 * @param maxZoom The maximum zoom level for the layer. At zoom levels equal to or greater than
 *   this, the layer will be hidden. A value in the range of `[0..24]`.
 * @param visible Whether the layer should be displayed.
 * @param opacity Background opacity. A value in range `[0..1]`.
 * @param color Background color.
 *
 *   Ignored if [pattern] is specified.
 *
 * @param pattern Image to use for drawing image fills. For seamless patterns, image width and
 *   height must be a factor of two (2, 4, 8, ..., 512). Note that zoom-dependent expressions will
 *   be evaluated only at integer zoom levels.
 */
@Composable
@Suppress("NOTHING_TO_INLINE")
public inline fun BackgroundLayer(
  id: String,
  minZoom: Float = 0.0f,
  maxZoom: Float = 24.0f,
  visible: Boolean = true,
  opacity: Expression.Float = const(1f),
  color: Expression.Color = const(Color.Black),
  pattern: Expression.ResolvedImage = nil(),
) {
  key(id) {
    LayerNode(
      factory = { BackgroundLayer(id = id) },
      update = {
        set(minZoom) { layer.minZoom = it }
        set(maxZoom) { layer.maxZoom = it }
        set(visible) { layer.visible = it }
        set(color) { layer.setBackgroundColor(it) }
        set(pattern) { layer.setBackgroundPattern(it) }
        set(opacity) { layer.setBackgroundOpacity(it) }
      },
      onClick = null,
      onLongClick = null,
    )
  }
}
