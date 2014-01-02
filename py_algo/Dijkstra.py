__author__ = 'Lucas'

"""
1  function Dijkstra(Graph, source):
2      for each vertex v in Graph:                                // Initializations
3          dist[v] := infinity ;                                  // Unknown distance function from
4                                                                 // source to v
5          previous[v] := undefined ;                             // Previous node in optimal path
6      end for                                                    // from source
7
8      dist[source] := 0 ;                                        // Distance from source to source
9      Q := the set of all nodes in Graph ;                       // All nodes in the graph are
10                                                                 // unoptimized - thus are in Q
11      while Q is not empty:                                      // The main loop
12          u := vertex in Q with smallest distance in dist[] ;    // Start node in first case
13          remove u from Q ;
14          if dist[u] = infinity:
15              break ;                                            // all remaining vertices are
16          end if                                                 // inaccessible from source
17
18          for each neighbor v of u:                              // where v has not yet been
19                                                                 // removed from Q.
20              alt := dist[u] + dist_between(u, v) ;
21              if alt < dist[v]:                                  // Relax (u,v,a)
22                  dist[v] := alt ;
23                  previous[v] := u ;
24                  decrease-key v in Q;                           // Reorder v in the Queue
25              end if
26          end for
27      end while
28  return dist;

"""