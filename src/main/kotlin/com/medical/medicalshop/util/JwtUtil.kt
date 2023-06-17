package com.medical.medicalshop.util

import com.medical.medicalshop.MedicalShopProperties
import com.medical.medicalshop.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil(private val medicalShopProperties: MedicalShopProperties) {

    private var secretKey: Key? = null

    fun generateToken(user: User): String {
        val claims: HashMap<String, Any> = HashMap()
//        claims["k"] = 5
        return createToken(claims, user.id)
    }

    fun extractId(token: String): Long {
        return extractClaim(token, Claims::getSubject).toLong()
    }

    fun isTokenValid(token: String): Boolean {
        return !isTokenExpired(token)
    }

    private fun createToken(claims: HashMap<String, Any>, userId: Long): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userId.toString())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(generateExpirationDate())
            .signWith(getKey())
            .compact()
    }

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis() + medicalShopProperties.expirationMs)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expirationDate: Date = extractExpiration(token)
        return expirationDate.before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun <T> extractClaim(token: String, claimsResolver: (claim: Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body
    }

    private fun getKey(): Key {
        if (secretKey != null) return secretKey as Key
        val newKey: Key = SecretKeySpec(medicalShopProperties.secret.toByteArray(), "HmacSHA256")
        secretKey = newKey
        return newKey
    }

}