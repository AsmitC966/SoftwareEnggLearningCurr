# loading.py
def read_csv(path):
    """
    Manually reads CSV files without the csv module.
    Skips headers and empty lines. Returns a list of lists.
    """
    data = []
    try:
        with open(path, 'r') as f:
            lines = f.readlines()
            if not lines:
                return data
            # Skip header row
            for line in lines[1:]:
                line = line.strip()
                if line:
                    data.append(line.split(','))
    except FileNotFoundError:
        print(f"Warning: {path} not found. No data loaded.")
    except Exception as e:
        print(f"Error reading {path}: {e}")
    return data