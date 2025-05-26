package org.maplibre.maplibrecompose.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.Updater
import androidx.compose.runtime.key
import org.maplibre.maplibrecompose.compose.FeaturesClickHandler
import org.maplibre.maplibrecompose.compose.MaplibreComposable
import org.maplibre.maplibrecompose.compose.engine.LayerNode
import org.maplibre.maplibrecompose.compose.engine.LocalStyleNode
import org.maplibre.maplibrecompose.compose.engine.MapNodeApplier
import org.maplibre.maplibrecompose.core.layer.Layer

@Composable
@MaplibreComposable
internal fun <T : Layer> LayerNode(
  factory: () -> T,
  update: Updater<LayerNode<T>>.() -> Unit,
  onClick: FeaturesClickHandler?,
  onLongClick: FeaturesClickHandler?,
) {
  val anchor = LocalAnchor.current
  val node = LocalStyleNode.current

  key(factory, anchor) {
    ComposeNode<LayerNode<T>, MapNodeApplier>(
      factory = { LayerNode(layer = factory(), anchor = anchor) },
      update = {
        if (!node.style.isUnloaded) {
          update()
          set(onClick) { this.onClick = it }
          set(onLongClick) { this.onLongClick = it }
        }
      },
    )
  }
}
