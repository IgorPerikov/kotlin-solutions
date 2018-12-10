package advent.y2018

import misc.readAdventInput

/**
 * https://adventofcode.com/2018/day/3
 */
const val notVisited = 0
const val visited = 1
const val collision = 2

fun main(args: Array<String>) {
    val claims = readClaims()
    val collisionsMap = buildCollisionsMap(claims)
    calculateCollisions(collisionsMap).also { println(it) }
    findIntactClaim(claims, collisionsMap).also { println(it) }
}

private fun calculateCollisions(collisionsMap: Array<Array<Int>>): Int {
    return collisionsMap.sumBy { ints -> ints.filter { it == collision }.sumBy { 1 } }
}

private fun findIntactClaim(claims: List<Claim>, collisionsMap: Array<Array<Int>>): Int {
    for (claim in claims) {
        var collisionsFound = 0
        outer@ for (i in claim.row until claim.row + claim.rows) {
            for (j in claim.column until claim.column + claim.columns) {
                if (collisionsMap[i][j] == collision) {
                    collisionsFound++
                    break@outer
                }
            }
        }
        if (collisionsFound == 0) {
            return claim.id
        }
    }
    throw IllegalArgumentException("No intact claims found")
}

private fun buildCollisionsMap(claims: List<Claim>): Array<Array<Int>> {
    val fabric = Array(1000) { Array(1000) { notVisited } }
    for (claim in claims) {
        for (i in claim.row until claim.row + claim.rows) {
            for (j in claim.column until claim.column + claim.columns) {
                when (fabric[i][j]) {
                    notVisited -> fabric[i][j] = visited
                    visited -> {
                        fabric[i][j] = collision
                    }
                }
            }
        }
    }
    return fabric
}

private fun readClaims() = readAdventInput(3, 2018).map(::parseClaim)

private fun parseClaim(claim: String): Claim {
    val (id, claimWithoutId) = claim.substringAfter("#").split(" @ ")
    val (column, row) = claimWithoutId.substringBefore(": ").split(",")
    val (columns, rows) = claimWithoutId.substringAfter(": ").split("x")
    return Claim(id.toInt(), column.toInt(), row.toInt(), columns.toInt(), rows.toInt())
}

private data class Claim(
    val id: Int,
    val column: Int,
    val row: Int,
    val columns: Int,
    val rows: Int
)
