import site
import pandas as pd
import numpy as np
import random
import math

df = pd.DataFrame()
df['some_column'] = [random.gauss(-4,4) for _ in range(10000)]
df['other_column'] = [random.gauss(1,4) for _ in range(10000)]

def CohenEffectSize(group1, group2):
    diff = group1.mean() - group2.mean()
    var1 = group1.var()
    var2 = group2.var()
    n1, n2 = len(group1), len(group2)
    pooled_var = (n1 * var1 + n2 * var2) / (n1 + n2)
    d = diff / math.sqrt(pooled_var)
    return d

CohenEffectSize(df.some_column, df.other_column)