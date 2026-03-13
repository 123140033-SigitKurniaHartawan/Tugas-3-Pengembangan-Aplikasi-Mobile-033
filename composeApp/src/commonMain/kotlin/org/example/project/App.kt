package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tugas3pam.composeapp.generated.resources.Res
import tugas3pam.composeapp.generated.resources.profile_photo
import org.jetbrains.compose.resources.painterResource

// Warna tema kustom
private val PrimaryBlue    = Color(0xFF1E88E5)
private val LightBlue      = Color(0xFFE3F2FD)
private val DarkText       = Color(0xFF1A1A2E)
private val SubText        = Color(0xFF6B7280)
private val BackgroundGray = Color(0xFFF8F9FA)
private val CardWhite      = Color(0xFFFFFFFF)

@Composable
fun App() {
    MaterialTheme {
        var showContactInfo by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // --- Header Profil ---
                ProfileHeader(
                    name = "Sigit Kurnia Hartawan",
                    nim  = "123140033",
                    bio  = "Mahasiswa Teknik Informatika Institut Teknologi Sumatera yang tertarik mengeksplorasi Mobile Application Development dan Data Mining."
                )

                Spacer(modifier = Modifier.height(28.dp))

                // --- Tombol Toggle Kontak ---
                Button(
                    onClick = { showContactInfo = !showContactInfo },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (showContactInfo) SubText else PrimaryBlue
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = if (showContactInfo) "✕  Sembunyikan Kontak" else "📋  Tampilkan Kontak",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // --- Card Kontak dengan AnimatedVisibility (Bonus) ---
                AnimatedVisibility(
                    visible = showContactInfo,
                    enter = fadeIn() + expandVertically(),
                    exit  = fadeOut() + shrinkVertically()
                ) {
                    ProfileCard(title = "Informasi Kontak") {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            InfoItem(emoji = "📧", label = "Email",    text = "sigit.123140033@student.itera.ac.id")
                            InfoDivider()
                            InfoItem(emoji = "📱", label = "Phone",    text = "+62 856 0935 3619")
                            InfoDivider()
                            InfoItem(emoji = "📍", label = "Location", text = "Bandar Lampung, Indonesia")
                        }
                    }
                }

                // Dorong footer ke bawah
                Spacer(modifier = Modifier.weight(1f))

                // --- Footer Copyright ---
                FooterCopyright()
            }
        }
    }
}

// ─────────────────────────────────────────────
// Composable 1: ProfileHeader
@Composable
fun ProfileHeader(
    name: String,
    nim: String,
    bio: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        // Foto profil circular
        Image(
            painter = painterResource(Res.drawable.profile_photo),
            contentDescription = "Foto Profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .shadow(elevation = 8.dp, shape = CircleShape)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nama
        Text(
            text = name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Badge NIM
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(LightBlue)
                .padding(horizontal = 14.dp, vertical = 4.dp)
        ) {
            Text(
                text = "NIM: $nim",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryBlue
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Bio
        Text(
            text = bio,
            fontSize = 14.sp,
            color = SubText,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}


// Composable 2: ProfileCard
@Composable
fun ProfileCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            Spacer(modifier = Modifier.height(14.dp))
            content()
        }
    }
}

// Composable 3: InfoItem
@Composable
fun InfoItem(
    emoji: String,
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(LightBlue),
            contentAlignment = Alignment.Center
        ) {
            Text(text = emoji, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = SubText,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = text,
                fontSize = 14.sp,
                color = DarkText,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// Composable 4: InfoDivider

@Composable
fun InfoDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(start = 56.dp),
        thickness = 0.8.dp,
        color = Color(0xFFE0E0E0)
    )
}

// Composable 5: FooterCopyright

@Composable
fun FooterCopyright(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        HorizontalDivider(
            thickness = 0.8.dp,
            color = Color(0xFFDDE1E7)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "© 2026 Tugas PAM 3 — 033",
            fontSize = 12.sp,
            color = SubText,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}