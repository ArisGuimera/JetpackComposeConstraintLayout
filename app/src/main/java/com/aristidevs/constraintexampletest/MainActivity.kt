package com.aristidevs.constraintexampletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.aristidevs.constraintexampletest.ui.theme.ConstraintExampleTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintExampleTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BoxConstraint()
                }
            }
        }
    }
}

@Preview
@Composable
fun BoxConstraint(){
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxGreen, boxMagenta, boxYellow) = createRefs()
        val topGuide = createGuidelineFromTop(0.2f)
        val startGuide = createGuidelineFromStart(0.2f)

        Box(modifier = Modifier.size(100.dp).background(Color.Red).constrainAs(boxRed){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(boxBlue.start)
        })
        Box(modifier = Modifier.size(100.dp).background(Color.Blue).constrainAs(boxBlue){
            top.linkTo(parent.top)
            start.linkTo(boxRed.end)
            end.linkTo(parent.end)
        })

        createHorizontalChain(boxRed, boxBlue, chainStyle = ChainStyle.SpreadInside)




        Box(modifier = Modifier.size(100.dp).background(Color.Green).constrainAs(boxGreen){
            top.linkTo(topGuide)
            start.linkTo(startGuide)
        })


        Box(modifier = Modifier.size(100.dp).background(Color.Magenta).constrainAs(boxMagenta){
            top.linkTo(boxYellow.bottom)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end, margin = 16.dp)
        })

        Box(modifier = Modifier.size(100.dp).background(Color.Yellow).constrainAs(boxYellow){
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

    }
}








