package com.lake.graph;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author lakeqiu
 */
public class ListGraph<V, E> extends Graph<V, E> {
    /**
     * vertices 顶点集合
     * edges 边集合
     */
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    /**
     * 边的权值比较器
     */
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    /**
     * 添加顶点
     * @param v 顶点
     */
    @Override
    public void addVertex(V v) {
        // 如果顶点集合中已经存在这个顶点，直接返回
        if (vertices.containsKey(v)) {
            return;
        }
        vertices.put(v, new Vertex<>(v));
    }

    /**
     * 添加边
     * @param from 出顶点
     * @param to 入顶点
     */
    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    /**
     * 添加边
     * @param from 出顶点
     * @param to 入顶点
     * @param weight 边权值
     */
    @Override
    public void addEdge(V from, V to, E weight) {
        // 获取起始顶点的节点
        Vertex<V, E> fromVertex = vertices.get(from);
        // 不存在就创建
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        // 创建对应的边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        // 判断这个边是否存在，存在的删除，为了更新边（如权值）
        // 判断起始顶点的出边中是否有一条边的终止节点为toVertex
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        // 然后再添加这条边
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        // 加入边集中
        edges.add(edge);
    }

    /**
     * 删除顶点
     * @param v
     */
    @Override
    public void removeVertex(V v) {
        // 从顶点集中删除对应顶点，如果顶点集中有这个顶点会返回
        Vertex<V, E> vertex = vertices.remove(v);
        // vertex等于null的话，说明顶点集中没有这个顶点，直接返回
        if (null == vertex) {
            return;
        }

        // 存在的话，删除该顶点相关的边（与这些边相关的其他顶点信息记得更新）

        // 删除与vertex顶点相关的出边
        // 边遍历边删除用迭代器
        for(Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
            // 获取出边
            Edge<V, E> edge = iterator.next();
            // 从出边edge的终止节点的入边集合中删除edge
            edge.to.inEdges.remove(edge);
            // 从vertex中删除edge
            iterator.remove();
            // 从边集中删除edge
            edges.remove(edge);
        }

        // 删除与vertex顶点相关的入边
        for(Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
            // 获取入边
            Edge<V, E> edge = iterator.next();
            // 从出边edge的起始节点的出边集合中删除edge
            edge.from.outEdges.remove(edge);
            // 从vertex中删除edge
            iterator.remove();
            // 从边集中删除edge
            edges.remove(edge);
        }
    }

    /**
     * 删除边
     * @param from 起始顶点
     * @param to 终止顶点
     */
    @Override
    public void removeEdge(V from, V to) {
        // 找到两个顶点
        Vertex<V, E> fromVertex = vertices.get(from);

        // 没有起始顶点，说明不存在这条边
        if (null == fromVertex) {
            return;
        }

        Vertex<V, E> toVertex = vertices.get(to);
        // 没有终止顶点，说明不存在这条边
        if (null == toVertex) {
            return;
        }

        // 到这里说明存在这条边，删除这两个顶点中的这条边的对应信息
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
        }

        // 删除边（从边集中删除）
        edges.remove(edge);
    }

    /**
     * 广度优先搜索
     * @param begin 开始顶点
     * @param visitor 要对顶点进行操作的接口
     */
    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        // 如果没有开始顶点，直接返回
        if (null == beginVertex) {
            return;
        }

        // 用来辅助遍历
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 用来存放已经入队(遍历过)的顶点
        Set<Vertex<V, E>> set = new HashSet<>();
        // 将开始顶点入队并放入set中
        queue.offer(beginVertex);
        set.add(beginVertex);

        while (!queue.isEmpty()) {
            // 出队遍历
            Vertex<V, E> vertex = queue.poll();
            if (visitor.visit(vertex.value)) {
                return;
            }

            for (Edge<V, E> outEdge : vertex.outEdges) {
                // 这条边的终止顶点已经入队了
                if (set.contains(outEdge.to)) {
                    continue;
                }
                // 将vertex通过一条边就可以到达的顶点入队
                queue.offer(outEdge.to);
                set.add(outEdge.to);
            }
        }
    }

    /**
     * 深度优先搜索
     * @param begin 开始顶点
     * @param visitor 要对顶点进行操作的接口
     */
    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        // 获取开始顶点
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }

        // 用来存放访问过的顶点
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        // 先访问起点
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        if (visitor.visit(begin)) {
            return;
        }

        while (!stack.isEmpty()) {
            // 将顶点出栈
            Vertex<V, E> vertex = stack.pop();

            // 从出栈的顶点中选择一条出边
            for (Edge<V, E> outEdge : vertex.outEdges) {
                // 判断一下这条边是否遍历过
                if (visitedVertices.contains(outEdge.to)) {
                    // 遍历过的话放弃这条边
                    continue;
                }

                // 出边没有遍历过
                // 将其起始顶点、终止顶点入栈
                stack.push(outEdge.from);
                stack.push(outEdge.to);

                // 将要访问过的顶点放入set中
                visitedVertices.add(outEdge.to);
                // 访问终止顶点
                if (visitor.visit(outEdge.to.value)) {
                    return;
                }
                break;
            }
        }

    }

    /**
     * 拓扑排序
     * @return 排序好的结果
     */
    @Override
    public List<V> topologicalSort() {
        // 用来存放排序结果的
        List<V> list = new ArrayList<>();
        // 存放度为0的顶点
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 维护各个顶点的入度实时信息
        Map<Vertex<V, E>, Integer> map = new HashMap<>();

        // 将入度为0的顶点放入队列中，不为0的顶点放入map中
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            // 获取顶点的入度数量
            int inSize = vertex.inEdges.size();

            if (inSize == 0) {
                queue.offer(vertex);
            } else {
                map.put(vertex, inSize);
            }
        });

        //
        while (!queue.isEmpty()) {
            // 出队元素，并放入list中
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);

            // 遍历vertex顶点的出边（为了得到下一个顶点）
            for (Edge<V, E> outEdge : vertex.outEdges) {
                // 获取在map中维护的这个顶点的入度,并且减1（vertex已经遍历过了，少了一个入度）
                Integer toIn = map.get(outEdge.to) - 1;

                // 如果度已经为0，那么加入queue中，否则，更新map
                if (toIn == 0) {
                    queue.offer(outEdge.to);
                } else {
                    map.put(outEdge.to, toIn);
                }

            }
        }
        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return prim();
    }

    /**
     * prim算法
     * @return
     */
    private Set<EdgeInfo<V, E>> prim() {
        // 1 先获取一个顶点(用迭代器)(获取最小生成树的起始顶点)
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        // 如果图中没有顶点，直接返回
        if (!iterator.hasNext()) {
            return null;
        }
        Vertex<V, E> vertex = iterator.next();

        // 2 切分定理
        // 用来存放最小生成树
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        // 用来存放已经是最小生成树的顶点
        Set<Vertex<V, E>> addVertices = new HashSet<>();

        // 将vertex顶点作为最小生成树的起始顶点放入addVertices中
        addVertices.add(vertex);
        // 最小堆，可以从中获取权值最小的边，进行切分，现在最小堆的边就是起始顶点向外的边
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);
        // 图中顶点数量
        int size = vertices.size();

        // 当堆为空并且最小生成树中顶点数量与图中的顶点数量相等时，最小生成树也就完成了
        while (!heap.isEmpty() && addVertices.size() < size) {
            // 从最小堆选出权值最小的边
            Edge<V, E> edge = heap.remove();
            // 如果这条边是最小生成树内顶点的连接边，则放弃这条边
            if (addVertices.contains(edge.to)) {
                continue;
            }
            // 加入最小生成树中
            edgeInfos.add(edge.info());
            // 将该边的终止节点加入最小生成树顶点集中
            addVertices.add(edge.to);
            // 将新加入的顶点的出边加入堆中
            heap.addAll(edge.to.outEdges);
        }
        return edgeInfos;

    }

    /**
     * kruskal算法
     * @return
     */
    private Set<EdgeInfo<V, E>> kruskal() {
        // edgeSize用来后面while循环进行判断（）
        int edgeSize = edgesSize() - 1;
        if (edgeSize < -1) {
            return null;
        }

        // 存放最小生成树
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();

        // 存放最小生成树的顶点
        Set<Vertex<V, E>> addVertices = new HashSet<>();
        // 最小堆，将图中所有边放进去，选择不会构成环的权值最小的边
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);

        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            // 获取最小生成树外权值最小的边
            Edge<V, E> edge = heap.remove();

            // 判断这条边加入最小生成树是否会构成环
            if (addVertices.contains(edge.to) && addVertices.contains(edge.from)) {
                continue;
            }

            edgeInfos.add(edge.info());
            addVertices.add(edge.to);
            addVertices.add(edge.from);
        }

        return edgeInfos;
    }

    /**
     * 最短路径
     * @param begin 起始顶点
     * @return
     */
    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
        return null;
    }

    /**
     * 最短路径 dijkstra 算法
     * @param begin 起始顶点
     * @return
     */
    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        // 存放已经拉起的路径
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        // 存放未拉起的路径
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        paths.put(beginVertex, new PathInfo<>(weightManager.zero()));
        // 初始化paths
//		for (Edge<V, E> edge : beginVertex.outEdges) {
//			PathInfo<V, E> path = new PathInfo<>();
//			path.weight = edge.weight;
//			path.edgeInfos.add(edge.info());
//			paths.put(edge.to, path);
//		}

        while (!paths.isEmpty()) {
            Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
            // minVertex离开桌面
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);
            // 对它的minVertex的outEdges进行松弛操作
            for (Edge<V, E> edge : minVertex.outEdges) {
                // 如果edge.to已经离开桌面，就没必要进行松弛操作
                if (selectedPaths.containsKey(edge.to.value)) {
                    continue;
                }
                // 进行松弛操作
                relaxForDijkstra(edge, minPath, paths);
            }
        }

        selectedPaths.remove(begin);
        return selectedPaths;
    }

    /**
     * 松弛
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
     */
    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        // 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        // 以前的最短路径：beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to);
        // 如果新路径比旧路径权值大
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) {
            return;
        }

        // 如果没有旧的路径的话创建新的路径，更新路径，否则清除旧的路径，填新值进去
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }

        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
    }

    /**
     * 从paths（即未来起来的）中挑一个最小的路径出来
     * @param paths
     * @return
     */
    private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();
        Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();
        while (it.hasNext()) {
            Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    /**
     * bellmanFord
     * @param begin
     * @return
     */
    @SuppressWarnings("unused")
    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        // 存放所有松弛的边
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        // 由于需要起始顶点信息，故加进去，并将权值设置为0（强制实现zero方法）
        selectedPaths.put(begin, new PathInfo<>(weightManager.zero()));

        // 进行n-1次松弛
        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) { // v - 1 次
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath == null) {
                    continue;
                }
                relax(edge, fromPath, selectedPaths);
            }
        }

        // 上面已经松弛完成了，这里是用来判断是否存在负权环
        // 然后存在，那么权值会再一次变更
        for (Edge<V, E> edge : edges) {
            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
            if (fromPath == null) {
                continue;
            }
            if (relax(edge, fromPath, selectedPaths)) {
                System.out.println("有负权环");
                return null;
            }
        }

        // 将最开始的起始顶点去除（一开始用来辅助的）
        selectedPaths.remove(begin);
        return selectedPaths;
    }


    /**
     * 松弛
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
     */
    private boolean relax(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {
        // 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        // 以前的最短路径：beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) {
            return false;
        }

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }

        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());

        return true;
    }


    /**
     * 顶点节点
     * @param <V>
     * @param <E>
     */
    private static class Vertex<V, E> {
        V value;
        /**
         * 记载对应顶点的出入度
         */
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();
        Vertex(V value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            return Objects.equals(value, ((Vertex<V, E>)obj).value);
        }
        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }
        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    /**
     * 边节点
     * @param <V>
     * @param <E>
     */
    private static class Edge<V, E> {
        /**
         * 记载这条边的开始顶点、终止顶点
         */
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>) obj;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }
        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }
    }
}
