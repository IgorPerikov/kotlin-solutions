package advent.y2018

import misc.readAdventInput

fun main(args: Array<String>) {
    val points = parsePoints()

    println(getSizeOfBiggestLimitedArea(points))
    println(getSizeOfSafeRegion(points))
}

private fun getSizeOfSafeRegion(points: Set<Point>): Int {
    var result = 0
    val maxX = points.map { it.x }.max()!!
    val maxY = points.map { it.y }.max()!!
    for (row in 0..maxY) {
        for (column in 0..maxX) {
            if (calcDistancesToAllPoints(row, column, points) < 10000) {
                result++
            }
        }
    }
    return result
}

private fun calcDistancesToAllPoints(row: Int, column: Int, points: Set<Point>): Int {
    return points.map { point -> manhattanDistance(column, row, point.x, point.y) }.sum()
}

private fun getSizeOfBiggestLimitedArea(points: Set<Point>): Int {
    val grid = generateGrid(points)
    grid.forEachIndexed { rowNumber, row ->
        row.forEachIndexed { column, _ ->
            val closestPoint = findClosestPoint(rowNumber, column, points)
            if (closestPoint != null) {
                grid[rowNumber][column] = closestPoint
            }
        }
    }
    return removeInfiniteAreas(grid, points.map { it.name })
        .map { pointName -> countPointEntries(grid, pointName) }
        .max()!!
}

private fun findClosestPoint(row: Int, column: Int, points: Set<Point>): Int? {
    val distances = points
        .map { point -> Pair(manhattanDistance(point.x, point.y, column, row), point.name) }
        .sortedBy { it.first }
    if (distances[0].first == distances[1].first) {
        return null
    } else {
        return distances[0].second
    }
}

private fun removeInfiniteAreas(grid: Array<IntArray>, pointNames: List<Int>): Set<Int> {
    val resultPoints = HashSet(pointNames)
    for (i in 0 until grid.size) {
        resultPoints.remove(grid[i][0])
        resultPoints.remove(grid[i][grid[0].size - 1])
    }
    for (i in 0 until grid[0].size) {
        resultPoints.remove(grid[0][i])
        resultPoints.remove(grid[grid.size - 1][i])
    }
    return resultPoints
}

private fun countPointEntries(grid: Array<IntArray>, pointName: Int): Int {
    return grid.iterator()
        .asSequence()
        .flatMap { it.asSequence() }
        .count { it == pointName }
}

private fun parsePoints(): Set<Point> {
    var i = 1
    return readAdventInput(6, 2018)
        .map {
            val coordinates = it.split(", ")
            Point(coordinates[0].toInt(), coordinates[1].toInt(), i++)
        }
        .toHashSet()
}

private fun generateGrid(points: Set<Point>): Array<IntArray> {
    val maxX = points.map { it.x }.max()!!
    val maxY = points.map { it.y }.max()!!
    return Array(maxY) { IntArray(maxX) { 0 } }
}

private fun manhattanDistance(x1: Int, y1: Int, x2: Int, y2: Int) = Math.abs(x1 - x2) + Math.abs(y1 - y2)

private data class Point(val x: Int, val y: Int, val name: Int)
