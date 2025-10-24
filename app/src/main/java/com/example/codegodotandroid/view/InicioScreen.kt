package com.example.codegodotandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codegodotandroid.R
import com.example.codegodotandroid.navigation.GodotColors

@Composable
fun InicioScreen(
    onNavigateToComunidad: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GodotColors.Background)
            .verticalScroll(scrollState)
    ) {
        // Hero Section con fondo azul
        HeroSection(onNavigateToComunidad)

        // Sección "¿Qué entregamos?"
        QueEntregamosSection()

        // Sección "¿Qué es Godot?"
        QueEsGodotSection()

        // Sección Herramientas
        HerramientasSection()

        // Espaciado final para asegurar que se vea todo
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun HeroSection(onNavigateToComunidad: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(GodotColors.Primary)
            .padding(vertical = 60.dp, horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Copiar y pegar",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Una experiencia agradable, fácil uso y ahorro de tiempo.\n\n" +
                        "CodeGodot es una página que te ayudará ahorrar tiempo y ayudando al aprendizaje con la rápida adquisición de código fácil y rápido que entregamos para tu proyecto de godot :)",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onNavigateToComunidad,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp)
            ) {
                Text(
                    text = "Comenzar",
                    color = GodotColors.Primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun QueEntregamosSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = GodotColors.Surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "¿Qué entregamos?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = GodotColors.OnBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Aquí encontrarás fragmentos de código listos para usar, ejemplos prácticos y soluciones rápidas que podrás copiar, pegar y adaptar a tus proyectos.",
                fontSize = 16.sp,
                color = GodotColors.OnBackground.copy(alpha = 0.9f),
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "En nuestra comunidad podrás:",
                fontSize = 16.sp,
                color = GodotColors.OnBackground,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            BulletPoint("Explorar códigos verificados y fácil de implementar.")
            BulletPoint("Guardar tus favoritos y dar \"me gusta\" a los que más te ayuden.")
            BulletPoint("Personalizar tu perfil y mostrar tu estilo de desarrollo.")
            BulletPoint("Subir tus propios códigos para ayudar a la comunidad y compartir tus propios códigos, llegar a otros desarrolladores y hacer crecer tu comunidad.")
            BulletPoint("Cada aporte será revisado por nuestros administradores para asegurar calidad y confianza.")
            BulletPoint("Este es tu lugar para aprender, compartir y crear sin límites.")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Únete hoy a CodeGodot y sé parte de la comunidad que transforma ideas en juegos.",
                fontSize = 16.sp,
                color = GodotColors.OnBackground.copy(alpha = 0.9f),
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Imagen del logo (placeholder por ahora)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "CodeGodot Logo",
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}

@Composable
fun QueEsGodotSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = GodotColors.Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "¿Qué es Godot?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = GodotColors.OnBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Godot es un motor de desarrollo de videojuegos libre y de código abierto, que permite crear juegos en 2D y 3D con facilidad. Su filosofía de nodos y escenas facilita la programación intuitiva. GDScript facilita tu creación de proyectos sin necesidad de conocimientos complejos, ideal tanto para principiantes como para desarrolladores experimentados.",
                fontSize = 16.sp,
                color = GodotColors.OnBackground.copy(alpha = 0.9f),
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "En nuestra página te proporcionamos fragmentos de código listos para usar en Godot, ahorrándote tiempo y guiándote en el desarrollo de mecánicas complejas y aplicar el código a tu proyecto acelerando tu flujo de trabajo y ayudándote a llevar tus ideas a la realidad más rápido.",
                fontSize = 16.sp,
                color = GodotColors.OnBackground.copy(alpha = 0.9f),
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Imagen de Godot (placeholder por ahora)
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Godot Engine",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}

@Composable
fun HerramientasSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = GodotColors.Surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Esta página tendrá herramientas que te harán la vida mas fácil! :)",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = GodotColors.OnBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Tarjeta 1: Rápido
            ToolCard(
                emoji = "⚡",
                title = "Rápido!",
                description = "La implementación de código será súper rápido, podrás copiar y pegar el código, tendrá una descripción cada publicación que te ayudará a entender de cómo hacerlo funcionar :)"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta 2: Gratis
            ToolCard(
                emoji = "🎁",
                title = "Gratis!",
                description = "Esta página será hasta sin fines de lucro, todo para que tú puedas usar y disfrutar de estos códigos y poder avanzar en sus proyectos personales o profesionales!"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta 3: Para todo mundo
            ToolCard(
                emoji = "🌍",
                title = "Para todo mundo!",
                description = "Esta página está hecha para todo el mundo, para que cualquiera pueda usarla, probar y sacar provecho a esta herramienta hecha con mucho cariño."
            )
        }
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "• ",
            fontSize = 16.sp,
            color = GodotColors.OnBackground.copy(alpha = 0.8f),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            color = GodotColors.OnBackground.copy(alpha = 0.8f),
            lineHeight = 24.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ToolCard(emoji: String, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = GodotColors.Background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Emoji en círculo
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = GodotColors.Primary.copy(alpha = 0.3f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = emoji,
                        fontSize = 32.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = GodotColors.OnBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                color = GodotColors.OnBackground.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {
    MaterialTheme {
        InicioScreen()
    }
}
