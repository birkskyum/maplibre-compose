package dev.sargunv.maplibrecompose.demoapp.demos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.compose.rememberStyleState
import dev.sargunv.maplibrecompose.core.util.PlatformUtils
import dev.sargunv.maplibrecompose.demoapp.DEFAULT_STYLE
import dev.sargunv.maplibrecompose.demoapp.Demo
import dev.sargunv.maplibrecompose.demoapp.DemoMapControls
import dev.sargunv.maplibrecompose.demoapp.DemoOrnamentSettings
import dev.sargunv.maplibrecompose.demoapp.DemoScaffold
import dev.sargunv.maplibrecompose.demoapp.FrameRateState
import dev.sargunv.maplibrecompose.demoapp.Platform
import dev.sargunv.maplibrecompose.demoapp.isNative
import kotlin.math.roundToInt

object FrameRateDemo : Demo {
  override val name = "Frame rate"
  override val description = "Change the frame rate of the map."

  @Composable
  override fun Component(navigateUp: () -> Unit) {
    DemoScaffold(this, navigateUp) {
      Column {
        val systemRefreshRate = PlatformUtils.getSystemRefreshRate().roundToInt()
        var maximumFps by remember { mutableStateOf(systemRefreshRate) }
        val fpsState = remember { FrameRateState() }

        val cameraState = rememberCameraState()
        val styleState = rememberStyleState()

        Box(modifier = Modifier.weight(1f)) {
          MaplibreMap(
            styleUri = DEFAULT_STYLE,
            maximumFps = maximumFps,
            onFrame = fpsState::recordFps,
            cameraState = cameraState,
            styleState = styleState,
            ornamentSettings = DemoOrnamentSettings(),
          )
          DemoMapControls(cameraState, styleState)
        }

        Column(modifier = Modifier.padding(16.dp)) {
          Slider(
            value = maximumFps.toFloat(),
            onValueChange = { maximumFps = it.roundToInt() },
            valueRange = 15f..systemRefreshRate.toFloat().coerceAtLeast(15f),
            enabled = Platform.isNative,
          )
          Text(
            "Target: $maximumFps ${fpsState.spinChar} Actual: ${fpsState.avgFps}",
            style = MaterialTheme.typography.labelMedium,
          )
        }
      }
    }
  }
}
